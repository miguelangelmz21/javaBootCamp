package com.example.demo.services;

import com.example.demo.dto.TagDTO;
import com.example.demo.entities.Tag;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    @Transactional(readOnly = true)
    public List<TagDTO> getAllTags() {
        return tagRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TagDTO getTagById(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tag not found with id: " + id));
        return convertToDTO(tag);
    }

    @Transactional(readOnly = true)
    public TagDTO getTagByName(String name) {
        Tag tag = tagRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Tag not found with name: " + name));
        return convertToDTO(tag);
    }

    @Transactional
    public TagDTO createTag(TagDTO tagDTO) {
        if (tagRepository.existsByName(tagDTO.getName())) {
            throw new IllegalArgumentException("Tag name already exists: " + tagDTO.getName());
        }

        Tag tag = convertToEntity(tagDTO);
        Tag savedTag = tagRepository.save(tag);
        return convertToDTO(savedTag);
    }

    @Transactional
    public TagDTO updateTag(Long id, TagDTO tagDTO) {
        Tag existingTag = tagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tag not found with id: " + id));

        if (!existingTag.getName().equals(tagDTO.getName()) &&
                tagRepository.existsByName(tagDTO.getName())) {
            throw new IllegalArgumentException("Tag name already exists: " + tagDTO.getName());
        }

        existingTag.setName(tagDTO.getName());

        if (tagDTO.getColor() != null && !tagDTO.getColor().isEmpty()) {
            existingTag.setColor(tagDTO.getColor());
        }

        Tag updatedTag = tagRepository.save(existingTag);
        return convertToDTO(updatedTag);
    }

    @Transactional
    public void deleteTag(Long id) {
        if (!tagRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tag not found with id: " + id);
        }
        tagRepository.deleteById(id);
    }

    private TagDTO convertToDTO(Tag tag) {
        TagDTO dto = new TagDTO();
        dto.setId(tag.getId());
        dto.setName(tag.getName());
        dto.setColor(tag.getColor());
        dto.setCreatedAt(tag.getCreatedAt());
        dto.setUpdatedAt(tag.getUpdatedAt());
        return dto;
    }

    private Tag convertToEntity(TagDTO dto) {
        Tag tag = new Tag();
        tag.setName(dto.getName());
        tag.setColor(dto.getColor() != null ? dto.getColor() : "#3498db");
        return tag;
    }
}
