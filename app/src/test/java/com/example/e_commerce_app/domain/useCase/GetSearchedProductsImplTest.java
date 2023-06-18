package com.example.e_commerce_app.domain.useCase;

import static org.mockito.Mockito.verify;

import com.example.e_commerce_app.data.repository.ProductsRepository;
import com.example.e_commerce_app.domain.model.ProductList;
import com.example.e_commerce_app.domain.result.Result;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.function.Function;

public class GetSearchedProductsImplTest {
    @Mock
    private ProductsRepository productsRepository;
    @Mock
    private Function<Result<ProductList>, Void> mockCallback;
    private GetSearchedProductsImpl getSearchedProductsImpl;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        getSearchedProductsImpl = new GetSearchedProductsImpl(productsRepository);
    }

    @Test
    public void GIVEN_the_call_to_GetSearchedProducts_WHEN_a_search_for_a_product_by_name_is_made_THEN_should_call_the_repository() {
        String productCategoryName = "iphone";
        getSearchedProductsImpl.call(mockCallback, productCategoryName);
        verify(productsRepository).getSearchedProducts(mockCallback, productCategoryName);
    }
}
