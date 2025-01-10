package team.emptyte.gui.annotated;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Application {
  @NotNull String title();

  int size() default 9;

  boolean canIntroduceItems() default true;
}
