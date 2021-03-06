package net.masterthought.dlanguage.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public interface DLanguageArrayInitializer extends PsiElement {
    @NotNull
    List<PsiElement> getOP_COMMAs();

    @NotNull
    List<DLanguageArrayMemberInitialization> getArrayMemberInitializations();

    @Nullable
    PsiElement getOP_BRACKET_RIGHT();

    @Nullable
    PsiElement getOP_BRACKET_LEFT();

}
