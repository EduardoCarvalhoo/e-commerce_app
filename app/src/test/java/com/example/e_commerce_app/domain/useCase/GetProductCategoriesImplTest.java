package com.example.e_commerce_app.domain.useCase;

import static org.mockito.Mockito.verify;

import com.example.e_commerce_app.data.repository.ProductsRepository;
import com.example.e_commerce_app.domain.model.ProductCategoryList;
import com.example.e_commerce_app.domain.result.Result;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.function.Function;

public class GetProductCategoriesImplTest {

    @Mock
    private ProductsRepository productsRepository;

    @Mock
    private Function<Result<ProductCategoryList>, Void> mockCallback;
    private GetProductCategoriesImpl getProductCategoriesImpl;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        getProductCategoriesImpl = new GetProductCategoriesImpl(productsRepository);
    }

    @Test
    public void GIVEN_the_call_to_GetProductCategories_WHEN_a_product_search_by_category_is_done_THEN_should_call_the_repository() {
        getProductCategoriesImpl.call(mockCallback);
        verify(productsRepository).getProductCategories(mockCallback);
    }
}

