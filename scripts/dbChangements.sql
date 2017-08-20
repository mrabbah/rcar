-- Table: patern_compteur

-- DROP TABLE patern_compteur;

CREATE TABLE patern_compteur
(
  id bigint NOT NULL,
  "version" bigint NOT NULL,
  libelle character varying(255) NOT NULL,
  numero_suivant integer NOT NULL,
  pas integer NOT NULL,
  prefixe character varying(255) NOT NULL,
  remplissage integer NOT NULL,
  suffixe character varying(255) NOT NULL,
  "type" character varying(13) NOT NULL,
  CONSTRAINT patern_compteur_pkey PRIMARY KEY (id)
)
WITH (OIDS=FALSE);
ALTER TABLE patern_compteur OWNER TO root;

-- Index: paterncompteur_libelle

-- DROP INDEX paterncompteur_libelle;

CREATE INDEX paterncompteur_libelle
  ON patern_compteur
  USING btree
  (libelle);

-- Index: paterncompteur_numero_suivant

-- DROP INDEX paterncompteur_numero_suivant;

CREATE INDEX paterncompteur_numero_suivant
  ON patern_compteur
  USING btree
  (numero_suivant);

-- Index: paterncompteur_pas

-- DROP INDEX paterncompteur_pas;

CREATE INDEX paterncompteur_pas
  ON patern_compteur
  USING btree
  (pas);

-- Index: paterncompteur_prefixe

-- DROP INDEX paterncompteur_prefixe;

CREATE INDEX paterncompteur_prefixe
  ON patern_compteur
  USING btree
  (prefixe);

-- Index: paterncompteur_remplissage

-- DROP INDEX paterncompteur_remplissage;

CREATE INDEX paterncompteur_remplissage
  ON patern_compteur
  USING btree
  (remplissage);

-- Index: paterncompteur_suffixe

-- DROP INDEX paterncompteur_suffixe;

CREATE INDEX paterncompteur_suffixe
  ON patern_compteur
  USING btree
  (suffixe);

-- Index: paterncompteur_type

-- DROP INDEX paterncompteur_type;

CREATE INDEX paterncompteur_type
  ON patern_compteur
  USING btree
  (type);



-- Column: sousTraite

-- ALTER TABLE voiture DROP COLUMN sousTraite;

ALTER TABLE voiture ADD COLUMN sous_traite boolean;
ALTER TABLE voiture ALTER COLUMN sous_traite SET STORAGE PLAIN;

UPDATE voiture
set sous_traite=false;


-- Column: prix achat par jour

-- ALTER TABLE location DROP COLUMN prixAchatParJour;

ALTER TABLE location ADD COLUMN  prix_achat_par_jour double precision;
ALTER TABLE location ALTER COLUMN prix_achat_par_jour SET STORAGE PLAIN;


UPDATE location
set prix_achat_par_jour=null;



-- Table: facture

-- DROP TABLE facture;

CREATE TABLE facture
(
  id bigint NOT NULL,
  "version" bigint NOT NULL,
  client character varying(255) NOT NULL,
  date timestamp without time zone NOT NULL,
  numero_facture character varying(255) NOT NULL,
  location_id bigint,
  CONSTRAINT facture_pkey PRIMARY KEY (id),
  CONSTRAINT fkbeeb477c24711c2a FOREIGN KEY (location_id)
      REFERENCES "location" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (OIDS=FALSE);
ALTER TABLE facture OWNER TO root;

INSERT INTO patern_compteur(
id, version, libelle, numero_suivant, pas, prefixe, remplissage, 
suffixe, type)
VALUES (363, 8, "Pattern facture", 1, 1, "FA{annee}{mois}{jour}", 3, 
'', "FACTURE"); 

-- Column: est_archive


-- ALTER TABLE location DROP COLUMN est_archive;


ALTER TABLE location ADD COLUMN est_archive boolean;

UPDATE location
set est_archive=false;