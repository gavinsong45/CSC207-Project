import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;


import com.google.cloud.translate.*;

public class TryAPI {
    public static void main(String[] args) {
        Translate translate = TranslateOptions.newBuilder().setApiKey("AIzaSyDGg_wMFNxhs-4Zh4FuAHe8lozim1OKH54").build().getService();
        Translation translation = translate.translate("¡Hola Mundo!", Translate.TranslateOption.targetLanguage("zh-CN"));
        System.out.printf("Translated Text:\n\t%s\n", translation.getTranslatedText());
    }
}