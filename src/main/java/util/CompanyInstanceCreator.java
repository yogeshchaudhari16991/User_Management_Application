package util;

import com.google.gson.InstanceCreator;
import model.Company;
import model.User;

import java.lang.reflect.Type;

/**
 * Created by yoges on 7/9/2016.
 */
public class CompanyInstanceCreator implements InstanceCreator<Company>{
    private final User user;

    public CompanyInstanceCreator(User user){
        this.user = user;
    }
    @Override
    public Company createInstance(Type type) {
        return new Company();
    }
}
