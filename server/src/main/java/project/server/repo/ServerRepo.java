package project.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.server.model.Server;

public interface ServerRepo extends JpaRepository<Server, Long> {
    Server findByIpAddress(String ipAddress);
}
