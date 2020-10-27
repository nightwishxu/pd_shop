package com.ruoyi.web.core.config;

import com.fasterxml.classmate.ResolvedType;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spi.service.contexts.ParameterContext;

public class DefaultParameterTypeReader implements ParameterBuilderPlugin {
    public DefaultParameterTypeReader() {
    }

    @Override
    public void apply(ParameterContext context) {
        context.parameterBuilder().parameterType(findParameterType(context));
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }

    public static String findParameterType(ParameterContext parameterContext) {
        ResolvedMethodParameter resolvedMethodParameter = parameterContext.resolvedMethodParameter();
        ResolvedType parameterType = resolvedMethodParameter.getParameterType();
        parameterType = parameterContext.alternateFor(parameterType);
        if (MultipartFile.class.isAssignableFrom(parameterType.getErasedType())) {
            return "form";
        } else if (resolvedMethodParameter.hasParameterAnnotation(PathVariable.class)) {
            return "path";
        } else if (resolvedMethodParameter.hasParameterAnnotation(ModelAttribute.class)) {
            return queryOrForm(parameterContext.getOperationContext());
        } else if (resolvedMethodParameter.hasParameterAnnotation(RequestBody.class)) {
            return "body";
        } else if (resolvedMethodParameter.hasParameterAnnotation(RequestParam.class)) {
            return queryOrForm(parameterContext.getOperationContext());
        } else if (resolvedMethodParameter.hasParameterAnnotation(RequestHeader.class)) {
            return "header";
        } else if (resolvedMethodParameter.hasParameterAnnotation(RequestPart.class)) {
            return "form";
        } else {
            return !resolvedMethodParameter.hasParameterAnnotations() ? queryOrForm(parameterContext.getOperationContext()) : queryOrForm(parameterContext.getOperationContext());
        }
    }

    private static String queryOrForm(OperationContext context) {
        return context.consumes().contains(MediaType.APPLICATION_FORM_URLENCODED) && context.httpMethod() == HttpMethod.POST ? "form" : "query";
    }
}
