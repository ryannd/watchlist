package com.ryannd.watchlist.features.list;

import com.ryannd.watchlist.features.list.model.EntryEntity;
import com.ryannd.watchlist.features.list.model.dto.EntryDto;
import com.ryannd.watchlist.features.user.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/list")
public class ListController {
  public final UserService userService;
  public final ListService listService;

  @Autowired
  public ListController(UserService userService, ListService listService) {
    this.userService = userService;
    this.listService = listService;
  }

  @GetMapping("/")
  public ResponseEntity<List<EntryEntity>> getList() {
    List<EntryEntity> watchList = this.userService.getAuthenticatedUser().getWatchList();
    return ResponseEntity.ok(watchList);
  }

  @PostMapping("/add")
  public ResponseEntity<Void> addToList(@RequestBody EntryDto entryDto) {
    listService.addToList(entryDto);
    return ResponseEntity.ok(null);
  }
}
