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

public class GetProductFavoriteImplTest {

    @Mock
    private ProductsRepository productsRepository;
    @Mock
    private Function<Result<ProductList>, Void> mockCallback;
    GetProductFavoriteImpl getProductFavoriteImpl;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        getProductFavoriteImpl = new GetProductFavoriteImpl(productsRepository);
    }

    @Test
    public void GIVEN_the_call_to_GetProductFavorite_WHEN_the_search_for_favorite_product_is_done_THEN_should_call_the_repository() {
        getProductFavoriteImpl.call(mockCallback);
        verify(productsRepository).getProductFavorite(mockCallback);
    }
}
