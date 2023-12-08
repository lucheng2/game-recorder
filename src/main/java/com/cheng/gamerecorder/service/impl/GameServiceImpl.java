package com.cheng.gamerecorder.service.impl;

import com.cheng.gamerecorder.dto.GameConfigDTO;
import com.cheng.gamerecorder.dto.GameRecordDTO;
import com.cheng.gamerecorder.dto.PlayerRecordDTO;
import com.cheng.gamerecorder.mapper.GameRecordMapper;
import com.cheng.gamerecorder.model.GameConfig;
import com.cheng.gamerecorder.model.GameRecord;
import com.cheng.gamerecorder.model.GameType;
import com.cheng.gamerecorder.model.Player;
import com.cheng.gamerecorder.repository.GameConfigRepository;
import com.cheng.gamerecorder.repository.GameRecordRepository;
import com.cheng.gamerecorder.repository.GameTypeRepository;
import com.cheng.gamerecorder.repository.PlayerRepository;
import com.cheng.gamerecorder.service.GameService;
import com.cheng.gamerecorder.vo.GameRecordSummaryVO;
import com.cheng.gamerecorder.vo.GameRecordVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.*;

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

    private final GameRecordMapper gameRecordMapper = GameRecordMapper.INSTANCE;

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

    @Override
    public void saveGameRecord(GameRecordDTO gameRecordDTO) {
        Optional<GameConfig> byId = gameConfigRepository.findById(gameRecordDTO.getGameConfigId());
        List<GameRecord> gameRecords = new ArrayList<>();
        Assert.isTrue(byId.isPresent(), "游戏不存在");
        GameConfig gameConfig = byId.get();
        Long maxGameSetId = getMaxGameSetId(gameConfig.getId());
        LocalDateTime now = LocalDateTime.now();
        for (PlayerRecordDTO playerRecord : gameRecordDTO.getPlayerRecords()) {
            Optional<Player> player = playerRepository.findById(playerRecord.getPlayerId());
            Assert.isTrue(player.isPresent(), "玩家不存在");
            Integer lastScore = getLastScore(gameConfig.getId(), player.get().getId(), maxGameSetId);
            GameRecord build = GameRecord.builder()
                    .gameConfig(gameConfig)
                    .player(player.get())
                    .score(playerRecord.getScore())
                    .totalScore(lastScore + playerRecord.getScore())
                    .gameSetId(maxGameSetId + 1)
                    .build();
            build.setCreateTime(now);
            gameRecords.add(build);
        }
        gameRecordRepository.saveAll(gameRecords);
    }

    @Override
    public List<List<GameRecordVO>> queryGameConfigByGameConfigId(Long gameConfigId) {
        Optional<GameConfig> byId = gameConfigRepository.findById(gameConfigId);
        Assert.isTrue(byId.isPresent(), "游戏不存在");
        List<List<GameRecordVO>> ans = new ArrayList<>();
        for (Player player : byId.get().getPlayers()) {
            List<GameRecord> gameRecords = gameRecordRepository.findAllByGameConfigIdAndPlayerId(gameConfigId, player.getId());
            Collections.sort(gameRecords);
            ans.add(gameRecordMapper.pos2vos(gameRecords));
        }
        return ans;
    }

    @Override
    public List<GameRecordSummaryVO> queryGameRecordSummary(Long gameConfigId) {
        Long maxGameSetId = gameRecordRepository.findMaxGameSetId(gameConfigId);
        List<GameRecord> gameRecords = gameRecordRepository.findAllByGameConfigIdAndGameSetId(gameConfigId, maxGameSetId);
        return gameRecordMapper.pos2summaryVOs(gameRecords);
    }

    Long getMaxGameSetId(Long gameConfigId) {
        Long maxGameSetId = gameRecordRepository.findMaxGameSetId(gameConfigId);
        return Objects.isNull(maxGameSetId) ? 0 : maxGameSetId;
    }

    Integer getLastScore(Long gameConfigId, Long playerId, Long gameSetId) {
        Optional<GameRecord> gameRecord = gameRecordRepository.findByGameConfigIdAndPlayerIdAndGameSetId(gameConfigId, playerId, gameSetId);
        return gameRecord.map(GameRecord::getScore).orElse(0);
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
