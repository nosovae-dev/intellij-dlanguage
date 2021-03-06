package net.masterthought.dlanguage.resolve.processors.basic

import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import net.masterthought.dlanguage.psi.interfaces.DNamedElement
import net.masterthought.dlanguage.resolve.processors.DResolveProcessor
import net.masterthought.dlanguage.utils.Identifier

/**
 * Created by francis on 6/15/2017.
 */
class DAllNameScopeProcessor(var start: Identifier) : DResolveProcessor<DNamedElement, DNamedElement> {
    override fun matches(call: DNamedElement, decl: DNamedElement): Boolean {
        return true
    }

    override val result = mutableSetOf<DNamedElement>()

    override fun execute(element: PsiElement, state: ResolveState): Boolean {
        if (element is DNamedElement) {
            if (element.name == start.name) {
                result.add(element)
            }
        } else {
            throw IllegalStateException()
        }
        return true
    }

}
