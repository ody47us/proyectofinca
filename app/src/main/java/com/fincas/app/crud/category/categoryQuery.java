package com.fincas.app.crud.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class categoryQuery {
    @Autowired
    private categoryRepository categoryRep;

    public List<categoryEntity> getAllcategories(){
        return (List<categoryEntity>) categoryRep.findAll();
    }
    public Optional<categoryEntity> getCategoryByID(long id){
        return categoryRep.findById(id);
    }
    public categoryEntity save(categoryEntity category){
        return categoryRep.save(category);
    }
    public void delete(categoryEntity farm){
        categoryRep.delete(farm);
    }
    public void deleteById(long id){
        categoryRep.deleteById(id);
    } 
}