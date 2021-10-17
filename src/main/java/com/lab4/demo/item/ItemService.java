package com.lab4.demo.item;

import com.lab4.demo.item.model.Item;
import com.lab4.demo.item.model.dto.ItemDTO;
import com.lab4.demo.item.model.dto.ItemFilterRequestDto;
import com.lab4.demo.review.Rating;
import com.lab4.demo.review.model.Review;
import com.lab4.demo.translation.Translation;
import com.lab4.demo.translation.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import static com.lab4.demo.item.ItemSpecifications.specificationsFromFilter;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final TranslationService translationService;

    private Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found: " + id));
    }

    private Page<ItemDTO> list(ItemFilterRequestDto filter, Pageable pageable) {
        return itemRepository.findAll(specificationsFromFilter(filter), pageable)
                .map(item -> {
                    Translation statusTranslation = translationService.findByCodeAndLanguage(item.getStatusCode(), "en");
                    double averageRating = item.getReviews().stream().map(Review::getRating).mapToDouble(Rating::getScore).average().orElse(.0f);
                    return ItemDTO.fromEntity(item, statusTranslation.getTranslation(), averageRating);
                });
    }

}
