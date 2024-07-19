package universityconnect.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universityconnect.dto.BlockDTO;
import universityconnect.service.BlockService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/blocks")
@RequiredArgsConstructor
public class BlockController {
    private final BlockService blockService;

    @PostMapping
    public ResponseEntity<BlockDTO> createBlock(@RequestBody BlockDTO blockDTO){
        BlockDTO block = blockService.createBlock(blockDTO);
        return new ResponseEntity<>(block, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BlockDTO>> getAllBlocks(){
        List<BlockDTO> blocks = blockService.getAllBlocks();
        return ResponseEntity.ok(blocks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlockDTO> getBlockById(@PathVariable long id){
        BlockDTO block = blockService.getBlockDTOById(id);
        return ResponseEntity.ok(block);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<BlockDTO> updateBlock(@PathVariable long id, @RequestBody BlockDTO blockDTO){
//        BlockDTO block = blockService.updateBlock(id, blockDTO);
//        return ResponseEntity.ok(block);
//    }

    @DeleteMapping("/{id}")
    public void deleteBlock(@PathVariable long id){
        blockService.deleteBlock(id);
    }
}
