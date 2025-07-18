package org.example.doogas.Model;

import org.springframework.stereotype.Component;
import org.example.doogas.Repository.ShelterRepostitory;
import org.springframework.stereotype.Service;

@Service
public class ShelterManager {
//    public static ShelterManager shelterManager;
    private final ShelterRepostitory shelterRepostitory;

    public ShelterManager(ShelterRepostitory shelterRepostitory) {
        this.shelterRepostitory = shelterRepostitory;
    }


    //    private ShelterManager(){}
//    public static ShelterManager of(){
//        if(shelterManager==null){
//            shelterManager=new ShelterManager();
//        }
//        return shelterManager;
//    }
    public Dog findDog(String name){
        return shelterRepostitory.findByName(name);
    }
    public void addDog(Dog dog){
        shelterRepostitory.save(dog);
    }
    public String manage(){
        long dogCount=shelterRepostitory.count();
        return "Managing shelter with " + dogCount + " dogs";

    }

    public long getDogCount() {
        return shelterRepostitory.count();
    }

    public void minusDog(Dog dog){
        shelterRepostitory.delete(dog);
    }
}
