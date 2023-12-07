package com.cheng.gamerecorder.service.impl;

import com.cheng.gamerecorder.dto.BasePageDTO;
import com.cheng.gamerecorder.dto.GameConfigDTO;
import com.cheng.gamerecorder.model.GameConfig;
import com.cheng.gamerecorder.model.GameType;
import com.cheng.gamerecorder.model.Player;
import com.cheng.gamerecorder.repository.GameConfigRepository;
import com.cheng.gamerecorder.repository.GameRecordRepository;
import com.cheng.gamerecorder.repository.GameTypeRepository;
import com.cheng.gamerecorder.repository.PlayerRepository;
import com.cheng.gamerecorder.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * service
 *
 * @author ChengLu
 * @date 2023/12/7
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class GameServiceImpl implements GameService {

    private final PlayerRepository playerRepository;

    private final GameTypeRepository gameTypeRepository;

    private final GameConfigRepository gameConfigRepository;

    private final GameRecordRepository gameRecordRepository;

    @Override
    public GameConfig saveGameConfig(GameConfigDTO dto) {
        if (Objects.nonNull(dto.getId())) {
            Optional<GameConfig> byId = gameConfigRepository.findById(dto.getId());
            if (byId.isPresent()) {
                GameConfig gameConfig = byId.get();
                gameConfig.setGameName(dto.getGameName());
                return gameConfigRepository.save(gameConfig);
            } else {
                log.warn("未找到游戏设置");
                return null;
            }
        }
        GameType gameType = findGameTypeOrSave(dto.getGameType(), dto.getPlayers().size());
        List<Player> players = findPlayersOrSave(dto.getPlayers());
        GameConfig build = GameConfig.builder()
                .gameName(dto.getGameName())
                .players(players)
                .gameType(gameType)
                .build();
        return gameConfigRepository.save(build);
    }

    @Override
    public List<GameConfig> findAllGameConfig() {
        return gameConfigRepository.findAll();
    }

    @Override
    public Page<GameConfig> queryGameConfig(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return gameConfigRepository.findAll(pageable);
    }

    @Override
    public void softDeleteGameConfig(Long id) {
        gameConfigRepository.softDelete(id);
    }

    GameType findGameTypeOrSave(String gameType, int playerNum) {
       GameType inDb = gameTypeRepository.findByGameType(gameType);
       if (Objects.nonNull(inDb)) {
           Assert.isTrue(playerNum == inDb.getPlayerNum(), "游戏人数不对");
           return inDb;
       }
       GameType build = GameType.builder()
               .gameType(gameType)
               .playerNum(playerNum)
               .build();
       return gameTypeRepository.save(build);
   }

   List<Player> findPlayersOrSave(List<String> playerNames) {
       List<Player> players = playerRepository.findAllByNameIn(playerNames);
       if (players.size() == playerNames.size()) {
           return players;
       }
       List<Player> created = new ArrayList<>();
       for (String playerName : playerNames) {
           if (players.stream().anyMatch(e -> Objects.equals(playerName, e.getName()))) {
               continue;
           }
           created.add(findPlayerOrSave(playerName));
       }
       players.addAll(created);
       return players;
   }

   Player findPlayerOrSave(String playerName) {
       Optional<Player> byName = playerRepository.findByName(playerName);
       if (byName.isPresent()) {
           return byName.get();
       }
       Player build = Player.builder()
               .name(playerName)
               .build();
       return playerRepository.save(build);
   }
}
