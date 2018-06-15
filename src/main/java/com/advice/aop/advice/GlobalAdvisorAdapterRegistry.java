package com.advice.aop.advice;

import com.advice.aop.advice.AdvisorAdapter;
import com.advice.aop.advice.AdvisorAdapterRegistry;
import com.advice.aop.advice.DefaultAdvisorAdapterRegistry;

/**
 * Created by yuch on 2018/6/15.
 */
public abstract class GlobalAdvisorAdapterRegistry {

    /**
     * Keep track of a single instance so we can return it to classes that request it.
     */
    private static com.advice.aop.advice.AdvisorAdapterRegistry instance = new com.advice.aop.advice.DefaultAdvisorAdapterRegistry();

    /**
     * Return the singleton {@link com.advice.aop.advice.DefaultAdvisorAdapterRegistry} instance.
     */
    public static com.advice.aop.advice.AdvisorAdapterRegistry getInstance() {
        return instance;
    }

    /**
     * Reset the singleton {@link com.advice.aop.advice.DefaultAdvisorAdapterRegistry}, removing any
     * {@link AdvisorAdapterRegistry#registerAdvisorAdapter(AdvisorAdapter) registered}
     * adapters.
     */
    static void reset() {
        instance = new DefaultAdvisorAdapterRegistry();
    }
}
