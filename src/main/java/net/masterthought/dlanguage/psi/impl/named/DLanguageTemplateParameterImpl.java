package net.masterthought.dlanguage.psi.impl.named;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.util.PsiTreeUtil;
import net.masterthought.dlanguage.psi.*;
import net.masterthought.dlanguage.psi.impl.DNamedStubbedPsiElementBase;
import net.masterthought.dlanguage.stubs.DLanguageTemplateParameterStub;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DLanguageTemplateParameterImpl extends DNamedStubbedPsiElementBase<DLanguageTemplateParameterStub> implements DLanguageTemplateParameter {

    public DLanguageTemplateParameterImpl(final DLanguageTemplateParameterStub stub, final IStubElementType type) {
        super(stub, type);
    }

    public DLanguageTemplateParameterImpl(final ASTNode node) {
        super(node);
    }

    public void accept(@NotNull final DLanguageVisitor visitor) {
        visitor.visitTemplateParameter(this);
    }

    public void accept(@NotNull final PsiElementVisitor visitor) {
        if (visitor instanceof DLanguageVisitor) accept((DLanguageVisitor) visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public DLanguageTemplateAliasParameter getTemplateAliasParameter() {
        return PsiTreeUtil.getChildOfType(this, DLanguageTemplateAliasParameter.class);
    }

    @Override
    @Nullable
    public DLanguageTemplateThisParameter getTemplateThisParameter() {
        return PsiTreeUtil.getChildOfType(this, DLanguageTemplateThisParameter.class);
    }

    @Override
    @Nullable
    public DLanguageTemplateTupleParameter getTemplateTupleParameter() {
        return PsiTreeUtil.getChildOfType(this, DLanguageTemplateTupleParameter.class);
    }

    @Override
    @Nullable
    public DLanguageTemplateTypeParameter getTemplateTypeParameter() {
        return PsiTreeUtil.getChildOfType(this, DLanguageTemplateTypeParameter.class);
    }

    @Override
    @Nullable
    public DLanguageTemplateValueParameter getTemplateValueParameter() {
        return PsiTreeUtil.getChildOfType(this, DLanguageTemplateValueParameter.class);
    }

    @Nullable
    public DLanguageIdentifier getNameIdentifier() {
        if (getTemplateAliasParameter() != null) {
            return getTemplateAliasParameter().getIdentifier();
        }
        if (getTemplateThisParameter() != null) {
            return getTemplateThisParameter().getTemplateTypeParameter().getIdentifier();
        }
        if (getTemplateTupleParameter() != null) {
            return getTemplateTupleParameter().getIdentifier();
        }
        if (getTemplateTypeParameter() != null) {
            return getTemplateTypeParameter().getIdentifier();
        }
        if (getTemplateValueParameter() != null) {
            return getTemplateValueParameter().getIdentifier();
        }
        throw new IllegalStateException("this shouldn't happen. Apparently theres some kind of template parameter that is neither, alias,this,tuple,type,or value");

    }
}
