package com.beecommerce.services;

import com.beecommerce.exception.ErrorCode;
import com.beecommerce.exception.Exception;
import com.beecommerce.models.Collection;
import com.beecommerce.repositories.CollectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectionService {
    @Autowired
    private final CollectionRepository collectionRepository;

    public Collection createCollection(Collection collection) {
        return collectionRepository.save(collection);
    }

    public Collection getCollectionById(String id) {
        return collectionRepository.findById(id)
                .orElseThrow(() -> new Exception(ErrorCode.COLLECTION_NOT_FOUND));
    }
}
