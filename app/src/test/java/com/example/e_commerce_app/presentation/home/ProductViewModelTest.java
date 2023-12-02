package com.example.e_commerce_app.presentation.home;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.e_commerce_app.domain.model.ProductCategoryList;
import com.example.e_commerce_app.domain.result.Result;
import com.example.e_commerce_app.domain.useCase.GetProductCategories;
import com.example.e_commerce_app.domain.useCase.GetProductFavorite;
import com.example.e_commerce_app.domain.useCase.GetProducts;
import com.example.e_commerce_app.domain.useCase.GetSearchedProducts;
import com.example.e_commerce_app.domain.useCase.GetSingleProduct;
import com.example.e_commerce_app.domain.useCase.SetFavoriteProductDeletion;
import com.example.e_commerce_app.domain.useCase.SetFavoriteProductSave;
import com.example.e_commerce_app.presentation.home.viewModel.ProductViewModel;
import com.example.e_commerce_app.utils.Exceptions.ServerErrorException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.function.Function;

public class ProductViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private GetProductCategories getProductCategories;
    @Mock
    private GetProducts getProducts;
    @Mock
    private GetSearchedProducts getSearchedProducts;
    @Mock
    private GetSingleProduct getSingleProduct;
    @Mock
    private SetFavoriteProductSave setFavoriteProductSave;
    @Mock
    private GetProductFavorite getProductFavorite;
    @Mock
    private SetFavoriteProductDeletion setFavoriteProductDeletion;
    @Captor
    ArgumentCaptor<Function<Result<ProductCategoryList>, Void>> productCategoryListCaptor;
    @Mock
    private ProductViewModel viewModel;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        viewModel = new ProductViewModel(
                getProductCategories, getProducts, getSearchedProducts,
                getSingleProduct, setFavoriteProductSave, getProductFavorite, setFavoriteProductDeletion
        );
    }

    @Test
    public void GIVEN_the_GetProductCategory_call_WHEN_the_callback_is_captured_THEN_a_success_result_is_posted() {
        viewModel.getProductCategory();
        ArrayList<String> categoryList = new ArrayList<>();
        categoryList.add("test");
        ProductCategoryList productCategoryList = new ProductCategoryList(categoryList);

        verify(getProductCategories).call(productCategoryListCaptor.capture());

        Function<Result<ProductCategoryList>, Void> callback = productCategoryListCaptor.getValue();
        callback.apply(new Result.Success<>(productCategoryList));

        assertEquals(categoryList, viewModel.productCategoryDataSuccessfullyReadLiveData.getValue());
    }

    @Test
    public void GIVEN_the_GetProductCategory_call_WHEN_the_callback_is_captured_THEN_a_error_result_is_posted() {
        viewModel.getProductCategory();
        ServerErrorException errorExpected = new ServerErrorException();

        verify(getProductCategories).call(productCategoryListCaptor.capture());

        Function<Result<ProductCategoryList>, Void> callback = productCategoryListCaptor.getValue();
        callback.apply(new Result.Error<>(errorExpected));

        assertEquals(errorExpected, viewModel.errorReadingProductCategoryDataLiveData.getValue());
    }
}