package util;

import com.google.gson.InstanceCreator;
import model.Company;

import java.lang.reflect.Type;

/**
 * Created by yoges on 7/9/2016.
 */
public class ComapnyInstanceCreator implements InstanceCreator<Company>{

    @Override
    public Company createInstance(Type type) {
        return new Company();
    }
}
