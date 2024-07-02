package universityconnect.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import universityconnect.domain.Block;
import universityconnect.domain.User;
import universityconnect.dto.BlockDTO;
import universityconnect.exception.ResourceNotFoundException;
import universityconnect.mapper.BlockMapper;
import universityconnect.repository.BlockRepository;
import universityconnect.repository.UserRepository;
import universityconnect.service.BlockService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlockServiceImpl implements BlockService {

    private final BlockRepository blockRepository;
    private final UserRepository userRepository;
    private final BlockMapper blockMapper;
    @Override
    public BlockDTO createBlock(BlockDTO blockDTO) {
        User blockedUser = userRepository.findById(blockDTO.getBlockedUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Blocked User ID is not found, id: " + blockDTO.getBlockedUserId()));
        User blockerUser = userRepository.findById(blockDTO.getBlockerUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Blocker User ID is not found, id: " + blockDTO.getBlockerUserId()));

        Block block = blockMapper.blockDTOToBlock(blockDTO);
        block.setBlockedUser(blockedUser);
        block.setBlockerUser(blockerUser);

        Block createdBlock = blockRepository.save(block);
        return blockMapper.blockToBlockDTO(createdBlock);
    }

    @Override
    public List<BlockDTO> getAllBlocks() {
        List<Block> blocks = blockRepository.findAll();
        return blocks.stream()
                .map(blockMapper::blockToBlockDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BlockDTO getBlockDTOById(long id) {
        Block block = blockRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Resource Id is not found, id: " + id));
        return blockMapper.blockToBlockDTO(block);
    }

    @Override
    public BlockDTO updateBlock(long id, BlockDTO blockDTO) {
        Block existingBlock = blockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Block ID is not found, id: " + id));

        if (blockDTO.getBlockedUserId() != null) {
            User blockedUser = userRepository.findById(blockDTO.getBlockedUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Blocked User ID is not found, id: " + blockDTO.getBlockedUserId()));
            existingBlock.setBlockedUser(blockedUser);
        }

        if (blockDTO.getBlockerUserId() != null) {
            User blockingUser = userRepository.findById(blockDTO.getBlockerUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Blocker User ID is not found, id: " + blockDTO.getBlockerUserId()));
            existingBlock.setBlockerUser(blockingUser);
        }

        Block block = blockRepository.save(existingBlock);
        return blockMapper.blockToBlockDTO(block);
    }

    @Override
    public void deleteBlock(long id) {
        Block block = blockRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Resource Id is not found, id: " + id));
        blockRepository.delete(block);

    }
}
