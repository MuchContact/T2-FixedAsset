CREATE TABLE fixedasset (
     id INT NOT NULL AUTO_INCREMENT,
     uniqueNumber VARCHAR(100) NOT NULL,
     originalWorth DOUBLE NOT NULL,
	 lifetime VARCHAR(50),
	 startDate VARCHAR(50),
     PRIMARY KEY (id)
);

CREATE TABLE depreciation (
     id INT NOT NULL AUTO_INCREMENT,
     accountDay VARCHAR(100) NOT NULL,
	 policy VARCHAR(200),
     PRIMARY KEY (id)
);

CREATE TABLE net_worth (
     id INT NOT NULL AUTO_INCREMENT,
     amount DOUBLE NOT NULL,
	 accountDay VARCHAR(200),
     accountant VARCHAR(200),
	 referencedFixedAssetId int,
	 referencedDepreciationId int,
     PRIMARY KEY (id)
);