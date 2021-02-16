package com.example.restsecurity.service.category;

import com.example.restsecurity.common.exception.CategoryNotFoundException;
import com.example.restsecurity.model.Category;
import com.example.restsecurity.model.Post;
import com.example.restsecurity.repository.CategoryRepository;
import com.example.restsecurity.service.AddSupported;
import com.example.restsecurity.service.DeleteSupported;
import com.example.restsecurity.service.GetSupported;
import com.example.restsecurity.service.UpdateSupported;
import com.example.restsecurity.transform.requset.category.CategoryRequest;
import com.example.restsecurity.transform.response.category.CategoryResponse;
import com.example.restsecurity.transform.response.post.PostGetResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements AddSupported<CategoryRequest, CategoryResponse>, UpdateSupported<CategoryResponse, CategoryRequest, Integer>, DeleteSupported<Integer>, GetSupported<Integer, CategoryResponse> {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public CategoryResponse add(CategoryRequest categoryRequest) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryRequest, category);
        Category savedCategory = categoryRepository.save(category);
        CategoryResponse categoryResponse = new CategoryResponse();
        BeanUtils.copyProperties(category, categoryResponse);

        return categoryResponse;
    }


    @Override
    public CategoryResponse update(CategoryRequest categoryRequest, Integer id) {
        boolean exist = categoryRepository.findById(id).isPresent();
        if (!exist) {
            throw new CategoryNotFoundException(id);

        }
        Category category = categoryRepository.findById(id).get();
        BeanUtils.copyProperties(categoryRequest, category);
        Category savedCategory = categoryRepository.save(category);
        CategoryResponse categoryResponse = new CategoryResponse();
        BeanUtils.copyProperties(savedCategory, categoryResponse);
        return categoryResponse;
    }


    @Override
    public void delete(Integer id) {
        boolean exist = categoryRepository.findById(id).isPresent();
        if (!exist) {
            throw new CategoryNotFoundException(id);

        }
        categoryRepository.deleteById(id);
    }


    @Override
    public CategoryResponse get(Integer id) {
        boolean exist = categoryRepository.findById(id).isPresent();
        if (!exist) {
            throw new CategoryNotFoundException(id);

        }
        Category category = categoryRepository.findById(id).get();
        CategoryResponse categoryResponse = new CategoryResponse();
        BeanUtils.copyProperties(category, categoryResponse);
        return categoryResponse;
    }

    @Override
    public List<CategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> categoryResponses = new ArrayList<>();

        for (Category category : categories) {
            CategoryResponse categoryResponse = new CategoryResponse();
            BeanUtils.copyProperties(category, categoryResponse);
            categoryResponses.add(categoryResponse);
        }
        return categoryResponses;
    }
}
