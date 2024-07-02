package universityconnect.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import universityconnect.domain.Block;
import universityconnect.dto.BlockDTO;
import universityconnect.repository.UserRepository;

@Mapper(componentModel = "spring")
public interface BlockMapper {

    BlockDTO blockToBlockDTO(Block block);

    Block blockDTOToBlock(BlockDTO blockDTO);

    @AfterMapping
    default void setUserFields(@MappingTarget BlockDTO blockDTO, Block block) {
        if (block.getBlockedUser() != null) {
            blockDTO.setBlockedUserId(block.getBlockedUser().getId());
            blockDTO.setBlockedUserName(block.getBlockedUser().getUsername());
        }
        if (block.getBlockerUser() != null) {
            blockDTO.setBlockerUserId(block.getBlockerUser().getId());
            blockDTO.setBlockerUserName(block.getBlockerUser().getUsername());
        }
    }

    @BeforeMapping
    default void setUserEntities(@MappingTarget Block block, BlockDTO blockDTO, UserRepository userRepository) {
        if (blockDTO.getBlockedUserId() != null) {
            block.setBlockedUser(userRepository.findById(blockDTO.getBlockedUserId()).orElse(null));
        }
        if (blockDTO.getBlockerUserId() != null) {
            block.setBlockerUser(userRepository.findById(blockDTO.getBlockerUserId()).orElse(null));
        }
    }
}
