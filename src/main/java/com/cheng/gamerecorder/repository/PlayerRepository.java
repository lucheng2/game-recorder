package com.cheng.gamerecorder.repository;

import com.cheng.gamerecorder.model.Player;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * repo
 *
 * @author ChengLu
 * @date 2023/12/7
 */
@Repository
public interface PlayerRepository extends BaseJpaRepository<Player, Long> {
    List<Player> findAllByNameIn(List<String> names);

    Optional<Player> findByName(String name);
}
