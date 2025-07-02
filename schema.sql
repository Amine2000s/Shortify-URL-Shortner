CREATE TABLE IF NOT EXISTS `url` (
                       `id` bigint(20) NOT NULL,
                       `date_created` date DEFAULT NULL,
                       `name` varchar(255) DEFAULT NULL,
                       `number_of_visits` int(11) NOT NULL,
                       `original_url` varchar(255) DEFAULT NULL,
                       `short_url` varchar(255) DEFAULT NULL,
                       `short_code` varchar(255) DEFAULT NULL,
                       `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

REATE TABLE `url_visit` (
  `visit_id` bigint(20) NOT NULL,
  `ip_adress` varchar(255) DEFAULT NULL,
  `referer` varchar(255) DEFAULT NULL,
  `timestamp` datetime(6) DEFAULT NULL,
  `user_agent` varchar(255) DEFAULT NULL,
  `id` bigint(20) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `url_visits` (
                              `url_id` bigint(20) NOT NULL,
                              `visits_visit_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `users` (
                         `id` bigint(20) NOT NULL,
                         `email` varchar(255) DEFAULT NULL,
                         `password` varchar(255) DEFAULT NULL,
                         `session_token` varchar(255) DEFAULT NULL,
                         `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


ALTER TABLE `url`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FKt9oxwarfpcswxu1l63hxawfsq` (`user_id`);

--
-- Indexes for table `url_visit`
--
ALTER TABLE `url_visit`
    ADD PRIMARY KEY (`visit_id`),
  ADD KEY `FKoipgo4b84x28y39l71f66hduh` (`id`);

--
-- Indexes for table `url_visits`
--
ALTER TABLE `url_visits`
    ADD UNIQUE KEY `UKfdulgi0o0gxeo6reqv42mvqh6` (`visits_visit_id`),
    ADD KEY `FKbmdvixs9vf4lrfrwycdtjahqk` (`url_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
    ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `url`
--
ALTER TABLE `url`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `url_visit`
--
ALTER TABLE `url_visit`
    MODIFY `visit_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `url`
--
ALTER TABLE `url`
    ADD CONSTRAINT `FKt9oxwarfpcswxu1l63hxawfsq` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `url_visit`
--
ALTER TABLE `url_visit`
    ADD CONSTRAINT `FKoipgo4b84x28y39l71f66hduh` FOREIGN KEY (`id`) REFERENCES `url` (`id`);

--
-- Constraints for table `url_visits`
--
ALTER TABLE `url_visits`
    ADD CONSTRAINT `FKbmdvixs9vf4lrfrwycdtjahqk` FOREIGN KEY (`url_id`) REFERENCES `url` (`id`),
  ADD CONSTRAINT `FKewrkl4hc1xjng83yixapmepxv` FOREIGN KEY (`visits_visit_id`) REFERENCES `url_visit` (`visit_id`);
COMMIT;

--