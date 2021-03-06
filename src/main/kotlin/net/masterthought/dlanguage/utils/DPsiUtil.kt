package net.masterthought.dlanguage.utils

import com.google.common.collect.Lists
import com.google.common.collect.Sets
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.PsiTreeUtil
import net.masterthought.dlanguage.psi.DLanguageDeclaration
import net.masterthought.dlanguage.psi.DLanguageFile
import net.masterthought.dlanguage.psi.DLanguageParameters
import net.masterthought.dlanguage.psi.DLanguageSingleImport
import net.masterthought.dlanguage.resolve.ParameterCountRange
import net.masterthought.dlanguage.resolve.processors.parameters.DAttributesFinder


object DPsiUtil {
    fun <T : PsiElement> getTexts(psiElements: List<T>): Array<String?> {
        val size = psiElements.size
        val result = arrayOfNulls<String>(size)
        for (i in 0..size - 1) {
            result[i] = psiElements[i].text
        }
        return result
    }

    private fun getDeclDefs(defs: PsiElement, declDefsList: MutableList<DLanguageDeclaration>): List<DLanguageDeclaration> {
        val declDefs = PsiTreeUtil.getChildOfType(defs, DLanguageDeclaration::class.java)
        if (declDefs != null) {
            declDefsList.add(declDefs)
            getDeclDefs(declDefs, declDefsList)

        }
        return declDefsList
    }

    /**
     * Returns a map of module -> alias for each imported module.  If a module is imported but not qualified, alias
     * will be null.
     */

    fun parseImports(file: PsiFile): Set<String> {
        val imports = Sets.newHashSet<String>()
        val declDefList = Lists.newArrayList<DLanguageDeclaration>()
        declDefList.addAll(PsiTreeUtil.findChildrenOfType(file, DLanguageDeclaration::class.java))
        for (declDef in declDefList) {
            val importDecls = PsiTreeUtil.findChildrenOfType(declDef, DLanguageSingleImport::class.java)
            for (importDecl in importDecls) {
                imports.add(importDecl.identifierChain!!.text)
            }
        }
        return imports
    }

    fun getParent(element: PsiElement, targetType: Set<IElementType>, excludedType: Set<IElementType>): PsiElement? {
        if (element.parent == null || element is DLanguageFile) {
            return null
        }

        if (targetType.contains(element.node.elementType)) {
            return element
        }

        if (excludedType.contains(element.node.elementType)) {
            return null
        }

        return getParent(element.parent, targetType, excludedType)

    }

    fun getNumParameters(parameters: DLanguageParameters, parameterContainer: PsiElement): ParameterCountRange {
        var min = 0
        var max = 0
        val finder = DAttributesFinder(parameterContainer)
        finder.recurseUp()
        if (!finder.isStatic()) {
            min++
            max++
        }
        for (parameter in parameters.parameters) {
            if (parameter.oP_EQ != null) {
                max++
                continue
            }
            val param = parameter.type?.type_2?.symbol?.identifierOrTemplateChain?.identifierOrTemplateInstances?.last()?.identifier
            if (param is Identifier) {
                val resolve = param.reference?.resolve()
                if (resolve is TemplateParameter) {//todo make this identifier resolving proof
                    if (resolve.templateTupleParameter != null) {
                        //then the paramater in question is actually a var arg
                        max = Integer.MAX_VALUE
                        break
                    }
                }
            }
            min++
            max++
        }
        if (parameters.oP_TRIPLEDOT != null) {
            max = Integer.MAX_VALUE
        }
        return ParameterCountRange(min, max)

    }
}

