# This file contains a make script for the CheckSumFile application
#
# Author: Josh McIntyre
#

SRC_FILES=src/com/jmcintyre/*.java
PKG=com.jmcintyre
PKG_PATH=com/jmcintyre
PKG_SUBPATH=com
ENTRY_CLASS=CheckSumFile

TEST_FILES=tests/com/jmcintyre/*.java
TEST_JUNIT_JAR=tests/junit.jar

RES_FILES=res/*.csv

OBJ_DIR=obj
BUILD_DIR=bin
BUILD_BIN=checksumfile.jar

CC=javac
PKGR=jar

# This rule builds the application
build: $(SRC_FILES) $(RES_FILES)
	mkdir -p $(BUILD_DIR)
	mkdir -p $(OBJ_DIR)
	$(CC) -d $(OBJ_DIR) $(SRC_FILES)
	cd $(OBJ_DIR); $(PKGR) cfe ../$(BUILD_DIR)/$(BUILD_BIN) $(PKG).$(ENTRY_CLASS) $(PKG_PATH)/*
	cd ../
	cp $(RES_FILES) $(BUILD_DIR)

# This rule builds unit tests
tests: $(TEST_FILES) $(SRC_FILES)
	mkdir -p $(BUILD_DIR)
	mkdir -p $(OBJ_DIR)
	$(CC) -cp $(TEST_JUNIT_JAR) -d $(OBJ_DIR) $(TEST_FILES) $(SRC_FILES)

# This rule cleans the build directory
clean: $(BUILD_DIR)
	rm $(BUILD_DIR)/*
	rmdir $(BUILD_DIR)
	rm $(OBJ_DIR)/$(PKG_PATH)/*
	rmdir $(OBJ_DIR)/$(PKG_PATH)
	rmdir $(OBJ_DIR)/$(PKG_SUBPATH)
	rmdir $(OBJ_DIR) 
