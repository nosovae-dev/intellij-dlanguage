package net.masterthought.dlanguage.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import net.masterthought.dlanguage.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class DLanguageCmpExpressionImpl extends ASTWrapperPsiElement implements DLanguageCmpExpression {
    public DLanguageCmpExpressionImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull DLanguageVisitor visitor) {
        visitor.visitCmpExpression(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof DLanguageVisitor) accept((DLanguageVisitor) visitor);
        else super.accept(visitor);
    }

    @Nullable
    public DLanguageShiftExpression getShiftExpression() {
        return PsiTreeUtil.getChildOfType(this, DLanguageShiftExpression.class);
    }

    @Nullable
    public DLanguageEqualExpression getEqualExpression() {
        return PsiTreeUtil.getChildOfType(this, DLanguageEqualExpression.class);
    }

    @Nullable
    public DLanguageIdentityExpression getIdentityExpression() {
        return PsiTreeUtil.getChildOfType(this, DLanguageIdentityExpression.class);
    }

    @Nullable
    public DLanguageRelExpression getRelExpression() {
        return PsiTreeUtil.getChildOfType(this, DLanguageRelExpression.class);
    }

    @Nullable
    public DLanguageInExpression getInExpression() {
        return PsiTreeUtil.getChildOfType(this, DLanguageInExpression.class);
    }
}
