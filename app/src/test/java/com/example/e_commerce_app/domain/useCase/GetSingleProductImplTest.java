package com.example.e_commerce_app.domain.useCase;

import static org.mockito.Mockito.verify;

import com.example.e_commerce_app.data.repository.ProductsRepository;
import com.example.e_commerce_app.domain.model.Product;
import com.example.e_commerce_app.domain.result.Result;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.function.Function;

public class GetSingleProductImplTest {

    @Mock
    private ProductsRepository productsRepository;
    @Mock
    private Function<Result<Product>, Void> mockCallback;
    private GetSingleProductImpl getSingleProductImpl;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        getSingleProductImpl = new GetSingleProductImpl(productsRepository);
    }

    @Test
    public void GIVEN_the_call_to_GetSingleProduct_WHEN_a_search_for_a_product_is_done_by_id_THEN_should_call_the_repository() {
        String productId = "1";
        getSingleProductImpl.call(mockCallback, productId);
        verify(productsRepository).getSingleProduct(mockCallback, productId);
    }
}
