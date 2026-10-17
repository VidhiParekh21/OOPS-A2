
package service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import model.annotation.Id;

public class Repository<T>{
    private final HashMap<String,T> data=new HashMap<>();

    public T save(T entity){
        try{
            Class<?> type=entity.getClass();
            Field idField=null;
            while(type!=null){
                for(Field field:type.getDeclaredFields()){
                    if(field.isAnnotationPresent(Id.class)){
                        idField=field;
                        break;
                    }
                }
                if(idField!=null) break;
                type=type.getSuperclass();
            }
            if(idField==null) throw new IllegalArgumentException("No @Id field found");
            idField.setAccessible(true);
            String id=String.valueOf(idField.get(entity));
            data.put(id,entity);
            return entity;
        }catch(IllegalAccessException e){
            throw new RuntimeException(e);
        }
    }

    public T findById(String id){
        return data.get(id);
    }

    public ArrayList<T> findAll(){
        return new ArrayList<>(data.values());
    }

    public boolean delete(String id){
        return data.remove(id)!=null;
    }

    @SafeVarargs
    public final void saveAll(T... items){
        for(T item:items){
            save(item);
        }
    }

    public static <N extends Number> double average(N[] values){
        if(values.length==0) return 0.0;
        double sum=0;
        for(N value:values){
            sum+=value.doubleValue();
        }
        return sum/values.length;
    }
}
