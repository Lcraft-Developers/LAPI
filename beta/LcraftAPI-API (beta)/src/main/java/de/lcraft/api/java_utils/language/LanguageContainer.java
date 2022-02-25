package de.lcraft.api.java_utils.language;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public interface LanguageContainer {
	@Nonnull ArrayList<String> allUsedTranslatedText();
}
