package com.example.e_commerce_app.domain.useCase;

import static org.mockito.Mockito.verify;

import com.example.e_commerce_app.data.repository.ProductsRepository;
import com.example.e_commerce_app.domain.model.Product;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SetFavoriteProductDeletionImplTest {
    @Mock
    private ProductsRepository productsRepository;
    @Mock
    Product product;
    SetFavoriteProductDeletionImpl setFavoriteProductDeletionImplTest;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        setFavoriteProductDeletionImplTest = new SetFavoriteProductDeletionImpl(productsRepository);
    }

    @Test
    public void GIVEN_the_call_to_SetFavoriteProductDeletion_WHEN_the_product_to_be_deleted_is_passed_THEN_must_call_the_repository() {
        setFavoriteProductDeletionImplTest.call(product);
        verify(productsRepository).setFavoriteProductDeletion(product);
    }
}
