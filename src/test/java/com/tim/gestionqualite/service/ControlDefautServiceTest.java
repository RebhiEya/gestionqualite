package com.tim.gestionqualite.service;

import com.tim.gestionqualite.entity.ControlDefect;
import com.tim.gestionqualite.entity.Produit;
import com.tim.gestionqualite.repository.ControlDefectRepository;
import com.tim.gestionqualite.repository.ProduitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ControlDefautServiceTest {


    @InjectMocks
    private ControlDefectService controlDefectService;
    @Mock
    ControlDefectRepository controlDefectRepository;

    @Test
    void ShouldAddProduct() {
        Long IdDefaut = 1L;
        ControlDefect controlDefect = mock(ControlDefect.class, RETURNS_DEEP_STUBS);
        controlDefect.setIdControlDefect(IdDefaut);
        ControlDefect controlDefect1 = new ControlDefect();
        controlDefectService.defectadd(controlDefect1);

        ArgumentCaptor<ControlDefect> defectIdArgumentCaptor = ArgumentCaptor.forClass(ControlDefect.class);
        verify(controlDefectRepository).save(defectIdArgumentCaptor.capture());
        ControlDefect defautIdCapture = defectIdArgumentCaptor.getValue();
        assertEquals(controlDefect1.getIdControlDefect(), defautIdCapture.getIdControlDefect());
    }
}
