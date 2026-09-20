import React, { useState } from 'react';
import {
  View,
  Text,
  ScrollView,
  StyleSheet,
  TouchableOpacity,
  Dimensions
} from 'react-native';
import { Image } from 'expo-image';
import { MaterialIcons, Feather } from '@expo/vector-icons';
import { router } from 'expo-router';
import { useBookStore } from '../../store/useBookStore';
import { SHELF_FOLDERS, ReadingStatus } from '../../data/sampleBooks';

const { width } = Dimensions.get('window');

type ShelfFilter = 'ALL' | ReadingStatus;

export default function BookshelfScreen() {
  const { bookshelf } = useBookStore();
  const [activeFilter, setActiveFilter] = useState<ShelfFilter>('ALL');
  const [isGridView, setIsGridView] = useState(false);

  const filteredBooks = bookshelf.filter((book) => {
    if (activeFilter === 'ALL') return true;
    return book.status === activeFilter;
  });

  return (
    <View style={styles.container}>
      {/* Header */}
      <View style={styles.header}>
        <View>
          <Text style={styles.headerTitle}>ชั้นหนังสือของฉัน</Text>
          <Text style={styles.headerSubtitle}>
            หนังสือทั้งหมด {bookshelf.length} เล่ม
          </Text>
        </View>
        <TouchableOpacity style={styles.importBtn}>
          <Feather name="upload" size={16} color="#C85A32" />
          <Text style={styles.importBtnText}>นำเข้าไฟล์</Text>
        </TouchableOpacity>
      </View>

      <ScrollView
        showsVerticalScrollIndicator={false}
        contentContainerStyle={styles.scrollContent}
      >
        {/* Status Filter Tabs & Layout Switch */}
        <View style={styles.filterRow}>
          <ScrollView
            horizontal
            showsHorizontalScrollIndicator={false}
            style={styles.filterTabs}
          >
            {[
              { id: 'ALL', label: 'ทั้งหมด' },
              { id: 'READING', label: 'กำลังอ่าน' },
              { id: 'UNREAD', label: 'ยังไม่อ่าน' },
              { id: 'FINISHED', label: 'อ่านจบแล้ว' }
            ].map((tab) => {
              const active = activeFilter === tab.id;
              return (
                <TouchableOpacity
                  key={tab.id}
                  onPress={() => setActiveFilter(tab.id as ShelfFilter)}
                  style={[styles.filterPill, active && styles.filterPillActive]}
                >
                  <Text
                    style={[
                      styles.filterText,
                      active && styles.filterTextActive
                    ]}
                  >
                    {tab.label}
                  </Text>
                </TouchableOpacity>
              );
            })}
          </ScrollView>

          <TouchableOpacity
            style={styles.viewToggleBtn}
            onPress={() => setIsGridView(!isGridView)}
          >
            <Feather
              name={isGridView ? 'list' : 'grid'}
              size={18}
              color="#4A5568"
            />
          </TouchableOpacity>
        </View>

        {/* Custom Folder Cards */}
        <View style={styles.foldersSection}>
          <Text style={styles.sectionTitle}>คอลเลกชันของฉัน</Text>
          <ScrollView horizontal showsHorizontalScrollIndicator={false}>
            {SHELF_FOLDERS.map((folder, index) => (
              <View key={index} style={styles.folderCard}>
                <View
                  style={[
                    styles.folderDot,
                    { backgroundColor: folder.colorHex }
                  ]}
                />
                <Text style={styles.folderName} numberOfLines={1}>
                  {folder.name}
                </Text>
                <Text style={styles.folderCount}>{folder.bookCount} เล่ม</Text>
              </View>
            ))}
          </ScrollView>
        </View>

        {/* Books Content */}
        <View style={styles.booksListContainer}>
          {filteredBooks.map((book) => (
            <TouchableOpacity
              key={book.id}
              activeOpacity={0.85}
              onPress={() => router.push(`/reader/${book.id}`)}
              style={styles.bookItemCard}
            >
              <Image source={{ uri: book.coverUrl }} style={styles.itemCover} />
              <View style={styles.itemDetails}>
                <View style={styles.itemTopRow}>
                  <Text style={styles.itemCategory}>{book.category}</Text>
                  {book.status === 'FINISHED' ? (
                    <View style={styles.finishedBadge}>
                      <MaterialIcons name="check" size={12} color="#4A7C59" />
                      <Text style={styles.finishedText}>จบแล้ว</Text>
                    </View>
                  ) : (
                    <Text style={styles.progressPercent}>
                      {book.readProgress || 0}%
                    </Text>
                  )}
                </View>

                <Text style={styles.itemTitle} numberOfLines={1}>
                  {book.title}
                </Text>
                <Text style={styles.itemAuthor} numberOfLines={1}>
                  {book.author}
                </Text>

                {book.readProgress !== undefined && (
                  <View style={styles.itemProgressBg}>
                    <View
                      style={[
                        styles.itemProgressFill,
                        { width: `${book.readProgress}%` }
                      ]}
                    />
                  </View>
                )}

                <View style={styles.itemActionRow}>
                  <Text style={styles.lastReadInfo}>
                    {book.lastReadText || 'เริ่มอ่านเล่มนี้'}
                  </Text>
                  <TouchableOpacity
                    style={styles.openReaderBtn}
                    onPress={() => router.push(`/reader/${book.id}`)}
                  >
                    <Text style={styles.openReaderText}>เปิดอ่าน</Text>
                  </TouchableOpacity>
                </View>
              </View>
            </TouchableOpacity>
          ))}
        </View>
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#FAF7F2'
  },
  header: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    paddingHorizontal: 20,
    paddingTop: 54,
    paddingBottom: 16
  },
  headerTitle: {
    fontSize: 22,
    fontWeight: '700',
    color: '#1A202C'
  },
  headerSubtitle: {
    fontSize: 12,
    color: '#718096',
    marginTop: 2
  },
  importBtn: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: '#FFF0EA',
    paddingHorizontal: 12,
    paddingVertical: 6,
    borderRadius: 16,
    gap: 4
  },
  importBtnText: {
    fontSize: 12,
    fontWeight: '600',
    color: '#C85A32'
  },
  scrollContent: {
    paddingBottom: 100
  },
  filterRow: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingHorizontal: 20,
    marginBottom: 16
  },
  filterTabs: {
    flex: 1
  },
  filterPill: {
    paddingHorizontal: 14,
    paddingVertical: 7,
    borderRadius: 18,
    backgroundColor: '#FFFFFF',
    marginRight: 8,
    borderWidth: 1,
    borderColor: '#E2E8F0'
  },
  filterPillActive: {
    backgroundColor: '#C85A32',
    borderColor: '#C85A32'
  },
  filterText: {
    fontSize: 12,
    fontWeight: '500',
    color: '#4A5568'
  },
  filterTextActive: {
    color: '#FFFFFF',
    fontWeight: '600'
  },
  viewToggleBtn: {
    padding: 8,
    backgroundColor: '#FFFFFF',
    borderRadius: 8,
    borderWidth: 1,
    borderColor: '#E2E8F0',
    marginLeft: 8
  },
  foldersSection: {
    paddingHorizontal: 20,
    marginBottom: 20
  },
  sectionTitle: {
    fontSize: 15,
    fontWeight: '700',
    color: '#1A202C',
    marginBottom: 10
  },
  folderCard: {
    width: 130,
    backgroundColor: '#FFFFFF',
    borderRadius: 12,
    padding: 12,
    marginRight: 10,
    borderWidth: 1,
    borderColor: '#E2E8F0'
  },
  folderDot: {
    width: 8,
    height: 8,
    borderRadius: 4,
    marginBottom: 8
  },
  folderName: {
    fontSize: 13,
    fontWeight: '600',
    color: '#1A202C',
    marginBottom: 2
  },
  folderCount: {
    fontSize: 11,
    color: '#718096'
  },
  booksListContainer: {
    paddingHorizontal: 20
  },
  bookItemCard: {
    flexDirection: 'row',
    backgroundColor: '#FFFFFF',
    borderRadius: 14,
    padding: 12,
    marginBottom: 12,
    borderWidth: 1,
    borderColor: '#E2E8F0'
  },
  itemCover: {
    width: 70,
    height: 100,
    borderRadius: 6
  },
  itemDetails: {
    flex: 1,
    marginLeft: 12,
    justifyContent: 'space-between'
  },
  itemTopRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center'
  },
  itemCategory: {
    fontSize: 11,
    color: '#718096',
    fontWeight: '500'
  },
  progressPercent: {
    fontSize: 12,
    fontWeight: '700',
    color: '#C85A32'
  },
  finishedBadge: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: '#EBF8F1',
    paddingHorizontal: 6,
    paddingVertical: 2,
    borderRadius: 4,
    gap: 2
  },
  finishedText: {
    fontSize: 10,
    fontWeight: '700',
    color: '#4A7C59'
  },
  itemTitle: {
    fontSize: 15,
    fontWeight: '700',
    color: '#1A202C'
  },
  itemAuthor: {
    fontSize: 12,
    color: '#718096'
  },
  itemProgressBg: {
    height: 4,
    backgroundColor: '#EDF2F7',
    borderRadius: 2,
    overflow: 'hidden',
    marginVertical: 4
  },
  itemProgressFill: {
    height: '100%',
    backgroundColor: '#C85A32'
  },
  itemActionRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center'
  },
  lastReadInfo: {
    fontSize: 11,
    color: '#A0AEC0'
  },
  openReaderBtn: {
    backgroundColor: '#C85A32',
    paddingHorizontal: 12,
    paddingVertical: 5,
    borderRadius: 12
  },
  openReaderText: {
    fontSize: 11,
    fontWeight: '600',
    color: '#FFFFFF'
  }
});
