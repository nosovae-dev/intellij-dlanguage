// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLanguageLambdaExpression extends PsiElement {

  @Nullable
  DLanguageIdentifier getIdentifier();

  @NotNull
  DLanguageAssignExpression getAssignExpression();

  @NotNull
  List<DLanguageFunctionAttribute> getFunctionAttributeList();

  @Nullable
  DLanguageParameters getParameters();

  @Nullable
  PsiElement getKwDelegate();

  @Nullable
  PsiElement getKwFunction();

  @NotNull
  PsiElement getOpLambdaArrow();

}
