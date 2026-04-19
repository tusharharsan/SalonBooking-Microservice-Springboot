package com.tushar.User.Service.Repository;

import com.tushar.User.Service.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User ,Long> {

}
