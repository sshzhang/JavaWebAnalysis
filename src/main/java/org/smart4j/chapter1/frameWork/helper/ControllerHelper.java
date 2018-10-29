package org.smart4j.chapter1.frameWork.helper;

import org.smart4j.chapter1.frameWork.annotation.Action;
import org.smart4j.chapter1.frameWork.bean.Handler;
import org.smart4j.chapter1.frameWork.bean.Request;
import org.smart4j.chapter1.frameWork.util.ArrayUtil;
import org.smart4j.chapter1.frameWork.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 控制器助手类
 */
public final  class ControllerHelper {


    /**
     * 用于存放请求与处理器的映射关系
     */
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static{
        System.out.println("ControllerHelper.class Init!");
        Set<Class<?>> controllerClassSet =
                ClassHelper.getControllerClassSet();

        if (CollectionUtil.isNotEmpty(controllerClassSet)) {

            for (Class<?> controllerClass : controllerClassSet) {

                //获取类中定义的方法
                Method[] methods = controllerClass.getMethods();
                if (ArrayUtil.isNotEmpty(methods)) {

                    for (Method method : methods) {

                        if (method.isAnnotationPresent(Action.class)) {


                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();

                            if (mapping.matches("\\w+:/\\w*")) {
                                String[] split = mapping.split(":");
                                if (ArrayUtil.isEmpty(split) && split.length == 2) {

                                    String requestMethod = split[0];
                                    String requestPath = split[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);
                                    ACTION_MAP.put(request, handler);
                                }
                            }


                        }

                    }

                }
            }


        }

    }


    /**
     * 获取　Handler
     */
    public static Handler getHandler(String requestMethod, String requestPath) {

        return ACTION_MAP.get(new Request(requestMethod, requestPath));
    }



}
