package com.example.e_commerce_app.domain.useCase;

import static org.mockito.Mockito.verify;

import com.example.e_commerce_app.data.repository.ProductsRepository;
import com.example.e_commerce_app.domain.model.Product;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SetFavoriteProductSaveImplTest {
    @Mock
    private ProductsRepository productsRepository;
    @Mock
    Product product;
    SetFavoriteProductSaveImpl setFavoriteProductSaveImpl;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        setFavoriteProductSaveImpl = new SetFavoriteProductSaveImpl(productsRepository);
    }

    @Test
    public void GIVEN_the_call_to_SetFavoriteProductSave_WHEN_the_product_to_be_saved_is_passed_THEN_must_call_the_repository() {
        setFavoriteProductSaveImpl.call(product);
        verify(productsRepository).saveFavoriteProduct(product);
    }
}
