package com.fincas.app.services;

import java.util.List;
import java.util.Optional;

import com.fincas.app.crud.category.categoryEntity;
import com.fincas.app.crud.category.categoryQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class categoryService {
    @Autowired
    private categoryQuery categoryRep;

    public List<categoryEntity> getAllCategories(){
        return categoryRep.getAllcategories();
    }

    public Optional<categoryEntity> getCategoryByID(long id){
        return categoryRep.getCategoryByID(id);
    }

    public categoryEntity saveCategory(categoryEntity category){
        if(category.getId() != null){
            Optional<categoryEntity> thisCategory = categoryRep.getCategoryByID(category.getId());

            if(thisCategory.isEmpty()){
                return categoryRep.save(category);
            }
            return null;
        }
        return categoryRep.save(category);
    }

    public categoryEntity update(categoryEntity category){
        if(category.getId() != null){
            Optional<categoryEntity> opCategory = categoryRep.getCategoryByID(category.getId());
            if(opCategory.isPresent()){
                categoryEntity thisCategory = opCategory.get();
                if(category.getName() != null){
                    thisCategory.setName(category.getName());
                }
                if(category.getDescription() != null){
                    thisCategory.setDescription(category.getDescription());
                }
                return categoryRep.save(category);
            }
        }
        return null;
    }

    public boolean delete(long id){
        Boolean isDeleted = this.categoryRep.getCategoryByID(id).map(category -> {
            categoryRep.delete(category);
            return true;
        }).orElse(false);
        return isDeleted;
    } 
}