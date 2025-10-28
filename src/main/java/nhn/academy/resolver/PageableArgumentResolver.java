package nhn.academy.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


public class PageableArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Pageable.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        String pageNumber = webRequest.getParameter("page");
        String pageSize = webRequest.getParameter("size");

        if (!StringUtils.hasText(pageNumber) || !StringUtils.hasText(pageSize)) {
            throw new IllegalArgumentException("page 또는 size의 입력값이 없습니다.");
        }

        try {
            if (Integer.parseInt(pageSize) > 50) {
                return PageRequest.of(Integer.parseInt(pageNumber),50);
            }
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("클라이언트에서 숫자로 변환할 수 없는 값 입력!");
        }

        return PageRequest.of(Integer.parseInt(pageNumber),10);
    }
}
