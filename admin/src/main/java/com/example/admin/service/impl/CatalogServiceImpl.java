package com.example.admin.service.impl;

import com.example.admin.service.CatalogService;
import com.example.commonmodel.dto.CatalogDTO;
import com.example.commonmodel.dto.DataTableRequestDTO;
import com.example.commonmodel.dto.DataTableResponseDTO;
import com.example.commonmodel.dto.ResponseDTO;
import com.example.dao.databinder.CatalogDataBinder;
import com.example.dao.entity.Catalog;
import com.example.dao.repoService.CatalogRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl extends BaseServiceImpl implements CatalogService {

    @Autowired
    CatalogRepoService catalogRepoService;

    @Override
    public ResponseDTO<CatalogDTO> createCatalog(CatalogDTO requestDTO) {
        ResponseDTO<CatalogDTO> responseDTO = new ResponseDTO<CatalogDTO>(Boolean.FALSE, getMessage("unable.to.save.record"), requestDTO);
        //TODO:- Validate Catalog eg check for duplicate catalog etc
        Catalog catalog = CatalogDataBinder.bind(requestDTO);
        try {
            catalog = catalogRepoService.save(catalog);
            requestDTO.setId(catalog.getId());
            responseDTO.setStatus(Boolean.TRUE);
            responseDTO.setMessage(getMessage("record.successfully.saved"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDTO;
    }

    public DataTableResponseDTO<CatalogDTO, List<CatalogDTO>> searchCatalog(DataTableRequestDTO<CatalogDTO> requestDTO) {
        return catalogRepoService.searchCatalog(requestDTO);
    }
}