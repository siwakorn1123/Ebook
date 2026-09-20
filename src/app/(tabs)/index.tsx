import React from 'react';
import {
  View,
  Text,
  ScrollView,
  StyleSheet,
  TouchableOpacity,
  TextInput,
  Dimensions
} from 'react-native';
import { Image } from 'expo-image';
import { LinearGradient } from 'expo-linear-gradient';
import { MaterialIcons, Feather } from '@expo/vector-icons';
import { router } from 'expo-router';
import { useBookStore } from '../../store/useBookStore';
import { CATEGORIES, BEST_SELLERS, AVATAR_URL } from '../../data/sampleBooks';

const { width } = Dimensions.get('window');

export default function HomeScreen() {
  const {
    activeReadingBook,
    selectedCategory,
    setSelectedCategory,
    userCoins
  } = useBookStore();

  return (
    <View style={styles.container}>
      {/* Top App Bar */}
      <View style={styles.appBar}>
        <View style={styles.brandContainer}>
          <View style={styles.brandIconWrapper}>
            <MaterialIcons name="menu-book" size={20} color="#FFFFFF" />
          </View>
          <View>
            <Text style={styles.brandName}>Readscape</Text>
            <Text style={styles.brandSub}>ร้านหนังสือ & อีบุ๊ก</Text>
          </View>
        </View>

        <View style={styles.appBarActions}>
          <TouchableOpacity style={styles.coinBadge}>
            <MaterialIcons name="monetization-on" size={16} color="#C85A32" />
            <Text style={styles.coinText}>{userCoins}</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.avatarButton}>
            <Image source={{ uri: AVATAR_URL }} style={styles.avatar} />
          </TouchableOpacity>
        </View>
      </View>

      <ScrollView
        showsVerticalScrollIndicator={false}
        contentContainerStyle={styles.scrollContent}
      >
        {/* Search Bar */}
        <View style={styles.searchBar}>
          <Feather name="search" size={20} color="#718096" />
          <TextInput
            placeholder="ค้นหาชื่อหนังสือ, นักเขียน, ISBN..."
            placeholderTextColor="#A0AEC0"
            style={styles.searchInput}
          />
          <TouchableOpacity style={styles.filterBtn}>
            <Feather name="sliders" size={18} color="#C85A32" />
          </TouchableOpacity>
        </View>

        {/* Categories Bar */}
        <ScrollView
          horizontal
          showsHorizontalScrollIndicator={false}
          style={styles.categoriesScroll}
        >
          {CATEGORIES.map((cat) => {
            const isSelected = selectedCategory === cat;
            return (
              <TouchableOpacity
                key={cat}
                onPress={() => setSelectedCategory(cat)}
                style={[
                  styles.categoryPill,
                  isSelected && styles.categoryPillSelected
                ]}
              >
                <Text
                  style={[
                    styles.categoryText,
                    isSelected && styles.categoryTextSelected
                  ]}
                >
                  {cat}
                </Text>
              </TouchableOpacity>
            );
          })}
        </ScrollView>

        {/* Hero Promo Banner */}
        <LinearGradient
          colors={['#C85A32', '#9E3E1B']}
          start={{ x: 0, y: 0 }}
          end={{ x: 1, y: 1 }}
          style={styles.heroCard}
        >
          <View style={styles.heroTextCol}>
            <View style={styles.promoTag}>
              <Text style={styles.promoTagText}>เทศกาลอ่านฟิน</Text>
            </View>
            <Text style={styles.heroTitle}>สัปดาห์หนังสือ E-book</Text>
            <Text style={styles.heroSubtitle}>
              ลดสูงสุด 50% ตลอดเดือนนี้ พร้อมรับคูปองเหรียญเงินคืนทันที
            </Text>
            <TouchableOpacity style={styles.heroButton}>
              <Text style={styles.heroButtonText}>สำรวจโปรโมชัน</Text>
              <MaterialIcons name="arrow-forward" size={16} color="#C85A32" />
            </TouchableOpacity>
          </View>
        </LinearGradient>

        {/* Continue Reading Section */}
        {activeReadingBook && (
          <View style={styles.sectionContainer}>
            <View style={styles.sectionHeader}>
              <Text style={styles.sectionTitle}>กำลังอ่านต่อ</Text>
              <TouchableOpacity onPress={() => router.push('/bookshelf')}>
                <Text style={styles.seeAllText}>ทั้งหมด</Text>
              </TouchableOpacity>
            </View>

            <TouchableOpacity
              activeOpacity={0.9}
              onPress={() => router.push(`/reader/${activeReadingBook.id}`)}
              style={styles.continueCard}
            >
              <Image
                source={{ uri: activeReadingBook.coverUrl }}
                style={styles.continueCover}
              />
              <View style={styles.continueInfo}>
                <View style={styles.badgeRow}>
                  <View style={styles.formatBadge}>
                    <Text style={styles.formatBadgeText}>EPUB</Text>
                  </View>
                  <Text style={styles.continueProgressText}>
                    {activeReadingBook.readProgress}%
                  </Text>
                </View>

                <Text style={styles.continueTitle} numberOfLines={1}>
                  {activeReadingBook.title}
                </Text>
                <Text style={styles.continueAuthor} numberOfLines={1}>
                  {activeReadingBook.author}
                </Text>

                {/* Progress bar */}
                <View style={styles.progressBarBg}>
                  <View
                    style={[
                      styles.progressBarFill,
                      { width: `${activeReadingBook.readProgress || 0}%` }
                    ]}
                  />
                </View>

                <View style={styles.continueFooter}>
                  <Text style={styles.remainingText}>
                    {activeReadingBook.timeRemaining}
                  </Text>
                  <View style={styles.readNowBtn}>
                    <Text style={styles.readNowText}>อ่านต่อ</Text>
                    <MaterialIcons name="play-arrow" size={16} color="#FFFFFF" />
                  </View>
                </View>
              </View>
            </TouchableOpacity>
          </View>
        )}

        {/* Best Sellers Carousel */}
        <View style={styles.sectionContainer}>
          <View style={styles.sectionHeader}>
            <Text style={styles.sectionTitle}>หนังสือขายดีประจำสัปดาห์</Text>
            <TouchableOpacity>
              <Text style={styles.seeAllText}>อันดับ 1-10</Text>
            </TouchableOpacity>
          </View>

          <ScrollView horizontal showsHorizontalScrollIndicator={false}>
            {BEST_SELLERS.map((book) => (
              <TouchableOpacity
                key={book.id}
                activeOpacity={0.8}
                onPress={() => router.push(`/book/${book.id}`)}
                style={styles.bookCard}
              >
                <View style={styles.bookCoverWrapper}>
                  <Image
                    source={{ uri: book.coverUrl }}
                    style={styles.bookCover}
                  />
                  {book.rank && (
                    <View style={styles.rankBadge}>
                      <Text style={styles.rankBadgeText}>{book.rank}</Text>
                    </View>
                  )}
                </View>

                <Text style={styles.bookTitle} numberOfLines={2}>
                  {book.title}
                </Text>
                <Text style={styles.bookAuthor} numberOfLines={1}>
                  {book.author}
                </Text>
                <View style={styles.priceRow}>
                  <Text style={styles.priceText}>฿{book.price}</Text>
                  {book.originalPrice && (
                    <Text style={styles.originalPriceText}>
                      ฿{book.originalPrice}
                    </Text>
                  )}
                </View>
              </TouchableOpacity>
            ))}
          </ScrollView>
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
  appBar: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    paddingHorizontal: 20,
    paddingTop: 54,
    paddingBottom: 12,
    backgroundColor: '#FAF7F2'
  },
  brandContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 10
  },
  brandIconWrapper: {
    width: 36,
    height: 36,
    borderRadius: 10,
    backgroundColor: '#C85A32',
    justifyContent: 'center',
    alignItems: 'center'
  },
  brandName: {
    fontSize: 18,
    fontWeight: '700',
    color: '#1A202C'
  },
  brandSub: {
    fontSize: 11,
    color: '#718096'
  },
  appBarActions: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 12
  },
  coinBadge: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: '#FFF0EA',
    paddingHorizontal: 10,
    paddingVertical: 5,
    borderRadius: 20,
    gap: 4
  },
  coinText: {
    fontSize: 13,
    fontWeight: '700',
    color: '#C85A32'
  },
  avatarButton: {
    width: 38,
    height: 38,
    borderRadius: 19,
    overflow: 'hidden',
    borderWidth: 1.5,
    borderColor: '#E2E8F0'
  },
  avatar: {
    width: '100%',
    height: '100%'
  },
  scrollContent: {
    paddingBottom: 100
  },
  searchBar: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: '#FFFFFF',
    marginHorizontal: 20,
    marginTop: 12,
    paddingHorizontal: 14,
    height: 48,
    borderRadius: 12,
    borderWidth: 1,
    borderColor: '#E2E8F0'
  },
  searchInput: {
    flex: 1,
    marginLeft: 10,
    fontSize: 14,
    color: '#1A202C'
  },
  filterBtn: {
    padding: 6
  },
  categoriesScroll: {
    marginVertical: 14,
    paddingLeft: 20
  },
  categoryPill: {
    paddingHorizontal: 16,
    paddingVertical: 8,
    borderRadius: 20,
    backgroundColor: '#FFFFFF',
    marginRight: 8,
    borderWidth: 1,
    borderColor: '#E2E8F0'
  },
  categoryPillSelected: {
    backgroundColor: '#C85A32',
    borderColor: '#C85A32'
  },
  categoryText: {
    fontSize: 13,
    fontWeight: '500',
    color: '#4A5568'
  },
  categoryTextSelected: {
    color: '#FFFFFF',
    fontWeight: '600'
  },
  heroCard: {
    marginHorizontal: 20,
    borderRadius: 16,
    padding: 20,
    marginTop: 4
  },
  heroTextCol: {
    width: '80%'
  },
  promoTag: {
    backgroundColor: 'rgba(255,255,255,0.2)',
    paddingHorizontal: 10,
    paddingVertical: 4,
    borderRadius: 8,
    alignSelf: 'flex-start',
    marginBottom: 8
  },
  promoTagText: {
    color: '#FFFFFF',
    fontSize: 12,
    fontWeight: '600'
  },
  heroTitle: {
    fontSize: 20,
    fontWeight: '700',
    color: '#FFFFFF',
    marginBottom: 6
  },
  heroSubtitle: {
    fontSize: 12,
    color: 'rgba(255,255,255,0.85)',
    lineHeight: 18,
    marginBottom: 14
  },
  heroButton: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: '#FFFFFF',
    paddingHorizontal: 14,
    paddingVertical: 8,
    borderRadius: 20,
    alignSelf: 'flex-start',
    gap: 4
  },
  heroButtonText: {
    color: '#C85A32',
    fontWeight: '700',
    fontSize: 13
  },
  sectionContainer: {
    marginTop: 24,
    paddingHorizontal: 20
  },
  sectionHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 12
  },
  sectionTitle: {
    fontSize: 17,
    fontWeight: '700',
    color: '#1A202C'
  },
  seeAllText: {
    fontSize: 13,
    color: '#C85A32',
    fontWeight: '600'
  },
  continueCard: {
    flexDirection: 'row',
    backgroundColor: '#FFFFFF',
    borderRadius: 16,
    padding: 14,
    borderWidth: 1,
    borderColor: '#E2E8F0'
  },
  continueCover: {
    width: 80,
    height: 115,
    borderRadius: 8
  },
  continueInfo: {
    flex: 1,
    marginLeft: 14,
    justifyContent: 'space-between'
  },
  badgeRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center'
  },
  formatBadge: {
    backgroundColor: '#EDF2F7',
    paddingHorizontal: 6,
    paddingVertical: 2,
    borderRadius: 4
  },
  formatBadgeText: {
    fontSize: 10,
    fontWeight: '700',
    color: '#4A5568'
  },
  continueProgressText: {
    fontSize: 12,
    fontWeight: '700',
    color: '#C85A32'
  },
  continueTitle: {
    fontSize: 15,
    fontWeight: '700',
    color: '#1A202C'
  },
  continueAuthor: {
    fontSize: 12,
    color: '#718096'
  },
  progressBarBg: {
    height: 6,
    backgroundColor: '#EDF2F7',
    borderRadius: 3,
    overflow: 'hidden'
  },
  progressBarFill: {
    height: '100%',
    backgroundColor: '#C85A32',
    borderRadius: 3
  },
  continueFooter: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center'
  },
  remainingText: {
    fontSize: 11,
    color: '#A0AEC0'
  },
  readNowBtn: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: '#C85A32',
    paddingHorizontal: 12,
    paddingVertical: 5,
    borderRadius: 14,
    gap: 2
  },
  readNowText: {
    fontSize: 11,
    color: '#FFFFFF',
    fontWeight: '600'
  },
  bookCard: {
    width: 130,
    marginRight: 14
  },
  bookCoverWrapper: {
    position: 'relative',
    borderRadius: 8,
    overflow: 'hidden',
    backgroundColor: '#E2E8F0'
  },
  bookCover: {
    width: 130,
    height: 185,
    borderRadius: 8
  },
  rankBadge: {
    position: 'absolute',
    top: 6,
    left: 6,
    width: 24,
    height: 24,
    borderRadius: 12,
    backgroundColor: '#C85A32',
    justifyContent: 'center',
    alignItems: 'center'
  },
  rankBadgeText: {
    color: '#FFFFFF',
    fontWeight: '700',
    fontSize: 12
  },
  bookTitle: {
    fontSize: 13,
    fontWeight: '700',
    color: '#1A202C',
    marginTop: 8
  },
  bookAuthor: {
    fontSize: 11,
    color: '#718096',
    marginTop: 2
  },
  priceRow: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 6,
    marginTop: 4
  },
  priceText: {
    fontSize: 13,
    fontWeight: '700',
    color: '#C85A32'
  },
  originalPriceText: {
    fontSize: 11,
    color: '#A0AEC0',
    textDecorationLine: 'line-through'
  }
});
