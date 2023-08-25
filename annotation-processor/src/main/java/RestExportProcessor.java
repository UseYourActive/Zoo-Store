import com.google.auto.service.AutoService;
import lombok.RequiredArgsConstructor;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@RequiredArgsConstructor
@AutoService(Processor.class)
@SupportedAnnotationTypes("com.tinqin.restexport.annotation.RestExport")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class RestExportProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        com.tinqin.restexport.RestExportProcessor processor = new com.tinqin.restexport.RestExportProcessor(roundEnv,
                "restexport/src/main/java",
                "com.example.zoostore.restexport",
                "ZooStoreRestExport");

        processor.processAnnotation();
        return false;
    }
}
