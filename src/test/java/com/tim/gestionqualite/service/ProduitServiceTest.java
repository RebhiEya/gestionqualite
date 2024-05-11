package com.tim.gestionqualite.service;

import com.tim.gestionqualite.entity.Produit;
import com.tim.gestionqualite.repository.ProduitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProduitServiceTest {

    @Mock
    private ProduitRepository produitRepository;
    @InjectMocks
    private ProduitService produitService;

    @Test
    void ShouldAddProduct() {
        Long IdProject = 1L;
        Produit produit = mock(Produit.class, RETURNS_DEEP_STUBS);
        produit.setIdProduit(IdProject);
        Produit produit1 = new Produit();
        produitService.addProduitWithoutChecklist(produit1);

        ArgumentCaptor<Produit> projectIdArgumentCaptor = ArgumentCaptor.forClass(Produit.class);
        verify(produitRepository).save(projectIdArgumentCaptor.capture());
        Produit projectIdCapture = projectIdArgumentCaptor.getValue();
        assertEquals(produit1.getIdProduit(), projectIdCapture.getIdProduit());
    }

    @Test
    void deleteProduit_WhenProduitDoesNotExist_ShouldThrowException() {
        // Arrange
        Long produitId = 1L;

        when(produitRepository.existsById(produitId)).thenReturn(false);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> produitService.deleteProduit(produitId));
        verify(produitRepository, never()).deleteById(anyLong());
    }
}
