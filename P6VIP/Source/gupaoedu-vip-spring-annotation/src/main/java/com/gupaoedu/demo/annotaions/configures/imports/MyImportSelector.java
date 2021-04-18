package com.gupaoedu.demo.annotaions.configures.imports;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by Tom.
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.gupaoedu.project.entity.Company",
                            "com.gupaoedu.project.entity.Member"};
    }
}
