package com.cheng.gamerecorder.mapper;

import com.cheng.gamerecorder.model.GameRecord;
import com.cheng.gamerecorder.vo.GameRecordSummaryVO;
import com.cheng.gamerecorder.vo.GameRecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * mapper
 *
 * @author ChengLu
 * @date 2023/12/8
 */
@Mapper
public interface GameRecordMapper {
    GameRecordMapper INSTANCE = Mappers.getMapper(GameRecordMapper.class);

    @Mapping(target = "name", source = "player.name")
    GameRecordVO po2vo(GameRecord record);

    @Mapping(target = "gameName", source = "gameConfig.gameName")
    @Mapping(target = "name", source = "player.name")
    GameRecordSummaryVO po2summaryVO(GameRecord record);

    List<GameRecordVO> pos2vos(List<GameRecord> records);

    List<GameRecordSummaryVO> pos2summaryVOs(List<GameRecord> records);

}
