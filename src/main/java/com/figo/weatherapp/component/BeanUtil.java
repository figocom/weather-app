package com.figo.weatherapp.component;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * USHBU CLASS BEAN BO'LMAGAN CLASS LAR ICHIDAN TURIB BEAN LAR NI OLISH UCHUN XIZMAT QILADI,
 * GET_BEAN METHODI ORQALI BEAN_FACTORY DAN SHU CLASS NOMIDAGI BEAN NI OLIB BERADI
 */
@Service
public class BeanUtil implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}
