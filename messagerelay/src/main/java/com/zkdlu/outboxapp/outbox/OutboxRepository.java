package com.zkdlu.outboxapp.outbox;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboxRepository extends JpaRepository<Outbox, Long> {
}
