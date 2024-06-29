package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.Block;
import universityconnect.dto.BlockDTO;

@Mapper
public interface BlockMapper {
    BlockMapper INSTANCE = Mappers.getMapper(BlockMapper.class);

    BlockDTO blockToBlockDTO(Block block);

    Block blockDTOToBlock(BlockDTO blockDTO);
}

