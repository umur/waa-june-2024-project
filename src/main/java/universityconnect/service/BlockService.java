package universityconnect.service;

import universityconnect.domain.Block;
import universityconnect.dto.BlockDTO;

import java.util.List;

public interface BlockService {

    BlockDTO createBlock(BlockDTO blockDTO);

    List<BlockDTO> getAllBlocks();

    BlockDTO getBlockDTOById(long id);

    BlockDTO updateBlock(long id,BlockDTO blockDTO);

    void deleteBlock(long id);
}
