package br.com.nt.easystay.infrastructure.repository;

import br.com.nt.easystay.domain.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface JpaRoomRepository extends JpaRepository<Room, Long> {

    @Transactional
    @Modifying
    @Query("update Room r set r.available = ?1 where r.id = ?2")
    int updateAvailableById(boolean available, Long id);

}
